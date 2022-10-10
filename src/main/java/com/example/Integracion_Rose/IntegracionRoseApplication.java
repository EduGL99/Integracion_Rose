package com.example.Integracion_Rose;

import com.example.Integracion_Rose.controller.RoseController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegracionRoseApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegracionRoseApplication.class, args);

		RoseController rose = new RoseController();
		//System.out.println(rose.createRose());
		//System.out.println(rose.editRose());
		//System.out.println(rose.closeRose());

		String projectId = new String();
		//System.out.println(rose.consultRose(projectId));
		//System.out.println(rose.consultRose());
		//System.out.println(rose.consultaMasiva());

	}

}
