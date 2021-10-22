package com.springdrools.demo.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.drools.core.io.impl.FileSystemResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DroolConfig {

	private KieServices kieServices = KieServices.Factory.get();

	private KieFileSystem getKieFileSystem() throws IOException {
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

		FileInputStream dtableFileStream = new FileInputStream("src/main/resources/rules/dtable.xlsx");
		FileInputStream drl = new FileInputStream("src/main/resources/rules/offer.drl");
		kieFileSystem
				.write("src/main/resources/rules/offer.drl", kieServices.getResources().newInputStreamResource(drl))
				.write("src/main/resources/rules/dtable.xlsx",
						kieServices.getResources().newInputStreamResource(dtableFileStream));
		// kieFileSystem.write(ResourceFactory.newClassPathResource("src/main/resources/rules/dtable.xlsx"));
		return kieFileSystem;

	}

	@Bean
	public KieContainer getKieContainer() throws IOException {
		log.info("Container created....");
		getKieRepository();
		KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
		kb.buildAll();
		KieModule kieModule = kb.getKieModule();
		KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
		return kContainer;

	}

	private void getKieRepository() {
		final KieRepository kieRepository = kieServices.getRepository();
		kieRepository.addKieModule(new KieModule() {
			public ReleaseId getReleaseId() {
				return kieRepository.getDefaultReleaseId();
			}
		});
	}

	@Bean
	public KieSession getKieSession() throws IOException {
		log.info("Session Created.....");
		return getKieContainer().newKieSession();

	}

}
