package com.letscode.ecommerce.services.Impl;

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
    public boolean novoCliente(ClienteDto clienteDto) {
        try {
            if (clienteDto.getId() == 0) { //Realmente é algo novo que está sendo criado (o Id não foi setado), por isso se faz necessário criptografar a senha pela primeira vez
                clienteDto.setSenha(passwordEncoder.encode(clienteDto.getSenha())); //Antes de salvar a senha, criptografar a mesma
            }
            Cliente cliente = new Cliente(clienteDto.getNome(), clienteDto.getSobrenome(), clienteDto.getEmail(),
            clienteDto.getSexo(), clienteDto.getCpf(), clienteDto.getSenha(), PerfilEnum.CLIENTE);
            clienteDao.save(cliente);
            cliente.setSenha(""); //Apenas para testes
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
