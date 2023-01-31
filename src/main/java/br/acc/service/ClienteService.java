package br.acc.service;

import br.acc.dto.ClienteDto;
import br.acc.entity.Cliente;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteService {

    public List<Cliente>  clienteList(){
        return Cliente.listAll();
    }

    @Transactional
    public Cliente saveCliente(ClienteDto dto){
        Cliente cliente = new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setIdade(dto.getIdade());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());

        cliente.persist();
        return cliente;
    }
    @Transactional
    public void updateCliente(Integer id, ClienteDto dto){
        Cliente cliente = new Cliente();
        Optional<Cliente> cliente1 = Cliente.findByIdOptional(id);

        if(cliente1.isEmpty()){
            throw new NullPointerException("Cliente não encontrado");
        }

        cliente = cliente1.get();
        cliente.setNome(dto.getNome());
        cliente.setIdade(dto.getIdade());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());

        cliente.persist();
    }
    @Transactional
    public void removeCliente(Integer id){
       Optional<Cliente> cliente1 = Cliente.findByIdOptional(id);

        if(cliente1.isEmpty()){
            throw new NullPointerException("Cliente não encontrado");
        }
        Cliente cliente = cliente1.get();
        cliente.delete();
    }


}
