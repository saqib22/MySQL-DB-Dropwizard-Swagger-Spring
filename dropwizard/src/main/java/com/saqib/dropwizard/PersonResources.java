package com.saqib.dropwizard;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.hashirahmad.spring.main.GWClass;
import com.hashirahmad.spring.model.Person;

@Path("/person")
@Api(value = "/person", description = "Person Database")
public class PersonResources {

	private final Validator validator_;

	public PersonResources(Validator validator) {
		validator_ = validator;
	}

	@GET
	@Path("/{personId}")
	@ApiOperation(value = "Get person", notes = "Get person", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server error !"),
			@ApiResponse(code = 404, message = "Not found !"),
			@ApiResponse(code = 401, message = "Unauthorized access !") })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@Context HttpHeaders httpHeader, @PathParam("personId") long personId) {
		GWClass gwClass = new GWClass("spring.xml");
		byte[] authHeader = httpHeader.getHeaderString(HttpHeaders.AUTHORIZATION).getBytes();
		Person person = gwClass.getPerson(personId, authHeader);
		if (person != null)
			return Response.ok(person).build();
		else
			return Response.status(401).entity("Unauthorized access!").build();
	}

	@POST
	@ApiOperation(value = "Add person.", notes = "Add person", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server error !"),
			@ApiResponse(code = 404, message = "Not found !"),
			@ApiResponse(code = 401, message = "Unauthorized access !") })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(@Valid PersonDTO personDataDTO) {
		GWClass gwClass = new GWClass("spring.xml");
		Person person = new Person();
		person.setId(personDataDTO.getId());
		person.setFirstName(personDataDTO.getFirstName());
		person.setLastName(personDataDTO.getLastName());
		person.setMobile(personDataDTO.getMobile());
		person.setUsername(personDataDTO.getUsername());
		person.setPassword(personDataDTO.getPassword());
		person.setAccessLevel(personDataDTO.getAccessLevel());
		if (gwClass.addPerson(person))
		    return Response.ok("Successfully registered!").build();
		else
		    return Response.status(500).entity("Error while registering. ID already exists!").build();
	}

	@DELETE
	@Path("/{personId}")
	@ApiOperation(value = "Delete person", notes = "Delete person", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Person deleted"),
			@ApiResponse(code = 500, message = "Internal Server error !"),
			@ApiResponse(code = 404, message = "Not found !"),
			@ApiResponse(code = 401, message = "Unauthorized access !") })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response del(@Context HttpHeaders httpHeader, @PathParam("personId") long personId) {
        GWClass gwClass = new GWClass("spring.xml");
        byte[] authHeader = httpHeader.getHeaderString(HttpHeaders.AUTHORIZATION).getBytes();
        if (gwClass.delPerson(personId, authHeader))
            return Response.ok("Successfully deleted!").build();
        else
            return Response.status(401).entity("Unauthorized access!").build();
	}

}
