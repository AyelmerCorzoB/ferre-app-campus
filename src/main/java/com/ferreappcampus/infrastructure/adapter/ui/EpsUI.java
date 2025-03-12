package com.ferreappcampus.infrastructure.adapter.ui;

import java.util.Scanner;

import com.ferreappcampus.application.usecase.EpsUseCase;
import com.ferreappcampus.infrastructure.database.ConnectMysqlFactory;
import com.ferreappcampus.infrastructure.persistence.EpsRepositoryImpl;

public class EpsUI {
    EpsRepositoryImpl repository = new EpsRepositoryImpl(ConnectMysqlFactory.crearConexion());
    EpsUseCase useCase = new EpsUseCase(repository);

    public void CreateEps(){
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Ingrese el nombre de la eps: ");
            String name = sc.nextLine();
            useCase.save(name);
        }
        
    }
}
