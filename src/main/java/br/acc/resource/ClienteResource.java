package br.acc.resource;

import br.acc.dto.ClienteDto;
import br.acc.entity.Cliente;
import br.acc.service.ClienteService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("clientes")
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    Validator validator;
    @Inject
    ClienteService service;


    @GET
    public Response listCliente(){
        List<Cliente> cliente = service.clienteList();
        return Response.ok(cliente).build();
    }
    @POST
    public Response saveCliente( @Valid ClienteDto dto){
        Cliente cliente = service.saveCliente(dto);
        return Response.ok(cliente).status(201).build();
    }

    @PUT
    @Path("{id}")
    public Response updateCliente(@PathParam("id") Integer id, ClienteDto dto){
        service.updateCliente(id, dto);
        return Response.status(204).build();
    }
    @DELETE
    @Path("{id}")
    public Response removeCliente(@PathParam("id") Integer id){
        service.removeCliente(id);
        return Response.status(204).build();
    }
}
