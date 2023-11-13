package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;

public class App 
{
    private static final String FILENAME = "pessoa.dat";
    public static void main( String[] args )
    {
        // Testa serialização e desserialização do JavaBean Pessoa
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Fulano");
        pessoa.setSobrenome("de Tal");
        pessoa.setDataNascimento(Date.valueOf("1987-06-05"));
        pessoa.setLocalNascimento("Alegrete/RS");
        pessoa.setNumeroDocumento("1234567890");
        pessoa.setEndereco("Rua dos Bobos, 0");
        pessoa.setTelefone("5551987654321");

        // Serializa o objeto para um arquivo
        try (FileOutputStream saidaArquivo = new FileOutputStream(FILENAME);
            ObjectOutputStream saidaObjeto = new ObjectOutputStream(saidaArquivo)) {

            saidaObjeto.writeObject(pessoa);
            System.out.printf("Objeto pessoa do tipo Pessoa serializado no arquivo %s\n", FILENAME);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Pessoa pessoaDesserializada = null;
        // Desserializa o objeto do arquivo
        try (FileInputStream entradaArquivo = new FileInputStream(FILENAME);
            ObjectInputStream entradaObjeto = new ObjectInputStream(entradaArquivo)) {

            pessoaDesserializada = (Pessoa)entradaObjeto.readObject();
            System.out.printf("Objeto pessoaDesserializada do tipo Pessoa desserializado do arquivo %s\n", FILENAME);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (pessoaDesserializada == null) {
            System.out.println("Não foi possível desserializar a pessoa");
        }
        else if (!pessoaDesserializada.getNome().equals(pessoa.getNome()) ||
                !pessoaDesserializada.getSobrenome().equals(pessoa.getSobrenome()) ||
                !pessoaDesserializada.getDataNascimento().equals(pessoa.getDataNascimento()) ||
                !pessoaDesserializada.getLocalNascimento().equals(pessoa.getLocalNascimento()) ||
                !pessoaDesserializada.getNumeroDocumento().equals(pessoa.getNumeroDocumento()) ||
                !pessoaDesserializada.getEndereco().equals(pessoa.getEndereco()) ||
                !pessoaDesserializada.getTelefone().equals(pessoa.getTelefone())) {
            
            System.out.println("Objeto desserializado está diferente do objeto original!");
            System.out.printf("Nome:\n > %s\n < %s\n", pessoa.getNome(), pessoaDesserializada.getNome());
            System.out.printf("Sobrenome:\n > %s\n < %s\n", pessoa.getSobrenome(), pessoaDesserializada.getSobrenome());
            System.out.printf("DataNascimento:\n > %s\n < %s\n", pessoa.getDataNascimento(), pessoaDesserializada.getDataNascimento());
            System.out.printf("LocalNascimento:\n > %s\n < %s\n", pessoa.getLocalNascimento(), pessoaDesserializada.getLocalNascimento());
            System.out.printf("NumeroDocumento:\n > %s\n < %s\n", pessoa.getNumeroDocumento(), pessoaDesserializada.getNumeroDocumento());
            System.out.printf("Endereco:\n > %s\n < %s\n", pessoa.getEndereco(), pessoaDesserializada.getEndereco());
            System.out.printf("Telefone:\n > %s\n < %s\n", pessoa.getTelefone(), pessoaDesserializada.getTelefone());
        }
        else {
            System.out.println("Serialização bem sucedida!");
        }

    }
}
