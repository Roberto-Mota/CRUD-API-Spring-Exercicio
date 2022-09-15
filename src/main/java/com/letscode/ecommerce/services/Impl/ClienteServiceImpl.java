package com.letscode.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.dto.ClienteDto;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.models.PerfilEnum;
import com.letscode.ecommerce.services.ClienteService;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteDao clienteDao;

    private final PasswordEncoder passwordEncoder;

    public ClienteServiceImpl(PasswordEncoder passwordEncoder) { // Receber o tipo de criptografia utilizada
        this.passwordEncoder = passwordEncoder;
    }

    public List<Cliente> listarTodosClientes() {
        return clienteDao.findAll();
    }

    public boolean atualizarCliente(Cliente cliente) {
        try {
            clienteDao.save(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removerCliente(long id) {
        try {
            clienteDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Cliente novoCliente(ClienteDto clienteDto) {
        try {
            System.out.println("ClienteDto recebeido: " + clienteDto.toString());
            System.out.println("Inicio do cadastro de um cliente");
            if (clienteDto.getId() == 0) { //Realmente é algo novo que está sendo criado (o Id não foi setado), por isso se faz necessário criptografar a senha pela primeira vez
                System.out.println("Primeiro cadastro identificado");
                clienteDto.setSenha(passwordEncoder.encode(clienteDto.getSenha())); //Antes de salvar a senha, criptografar a mesma
                System.out.println("Senha pós encoder: " + clienteDto.getSenha());
                System.out.println("Criptografia bem sucedida");
            }
            Cliente cliente = new Cliente(clienteDto.getNome(), clienteDto.getSobrenome(), clienteDto.getEmail(),
            clienteDto.getSexo(), clienteDto.getCpf(), clienteDto.getSenha(), PerfilEnum.CLIENTE);
            System.out.println("Criação de Objeto bem sucedida -?-");
            System.out.println("Cliente: " + cliente.toString());
            System.out.println("ClienteDto: " + clienteDto.toString());
            clienteDao.save(cliente);
            System.out.println("Cliente salvo com sucesso");
            //cliente.setSenha("1234"); //Apenas para testes
            return cliente;
        } catch (Exception e) {
            return null;
        }
    }
}
