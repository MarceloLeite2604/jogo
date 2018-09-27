package org.marceloleite.jogo.servidor;

import javax.inject.Inject;

import org.marceloleite.jogo.servidor.business.JogoBO;
import org.marceloleite.jogo.servidor.configuracao.JogoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JogoFactory.class)
public class Main {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	@Inject
	private JogoBO jogoBO;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
        return args -> {
        	LOGGER.debug("Programa iniciado.");
        	jogoBO.inicializar();
        };
    }

}
