package segundum.infrastructure.rest.controllers;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import segundum.application.commands.DeleteUserCommand;
import segundum.application.commands.RegisterUserCommand;
import segundum.application.commands.UpdateUserCommand;
import segundum.application.queries.GetUserProfileQuery;
import segundum.application.usecases.DeleteUserUseCase;
import segundum.application.usecases.GetUserProfileUseCase;
import segundum.application.usecases.RegisterUserUseCase;
import segundum.application.usecases.UpdateUserProfileUseCase;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.requests.RegisterUserRequest;
import segundum.infrastructure.rest.requests.UpdateUserProfileRequest;
import segundum.infrastructure.rest.responses.mappers.UserResponseMapper;

/**
 * REST controller for user operations.
 * <p>
 * Dependencies are injected via constructor parameter by the Composition Root
 * ({@link segundum.infrastructure.rest.config.ApplicationConfig}),
 * avoiding framework annotations in application layers.
 * </p>
 */
@Path("/users")
public class UserController {
	
	/**
	 * The URI information for building response locations.
	 */
	@Context
	private UriInfo uriInfo;
	
	/**
	 * The use case for registering a new user.
	 */
	private final RegisterUserUseCase registerUserUseCase;
	
	/**
	 * The use case for updating an existing user.
	 */
	private final UpdateUserProfileUseCase updateUserUseCase;
	
	/**
	 * The use case for deleting an existing user.
	 */
	private final DeleteUserUseCase deleteUserUseCase;

	/**
	 * The use case for retrieving a user profile.
	 */
	private final GetUserProfileUseCase getUserProfileUseCase;
	
	/**
	 * Constructs a new UserController with the given use cases.
	 *
	 * @param registerUserUseCase   the use case for user registration
	 * @param updateUserUseCase     the use case for updating user profiles
	 * @param deleteUserUseCase     the use case for deleting users
	 * @param getUserProfileUseCase the use case for retrieving user profiles
	 */
	public UserController(RegisterUserUseCase registerUserUseCase,
			UpdateUserProfileUseCase updateUserUseCase,
			DeleteUserUseCase deleteUserUseCase,
			GetUserProfileUseCase getUserProfileUseCase) {
		this.registerUserUseCase = registerUserUseCase;
		this.updateUserUseCase = updateUserUseCase;
		this.deleteUserUseCase = deleteUserUseCase;
		this.getUserProfileUseCase = getUserProfileUseCase;
	}
	
	/**
	 * Registers a new user.
	 * 
	 * @param request the registration request data
	 * @return a response with the created user and the location header
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerUser(RegisterUserRequest request) {
		RegisterUserCommand command = new RegisterUserCommand(
				new Name(request.getName()),
				new Surname(request.getSurname()),
				new Email(request.getEmail()),
				new Password(request.getPassword()),
				new Birthdate(request.getBirthdate()),
				new Phone(request.getPhone())
		);
		User user = registerUserUseCase.execute(command);
		URI location = uriInfo.getAbsolutePathBuilder()
				.path(user.getUserId().getValue().toString())
				.build();
		return Response.created(location)
				.entity(UserResponseMapper.fromDomain(user))
				.build();
	}
	
	/**
	 * Updates an existing user's profile partially.
	 * 
	 * @param id the unique identifier of the user to update
	 * @param request the request data with the fields to update
	 * @return a response with the updated user
	 */
	@PATCH
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUserProfile(@PathParam("id") String id, UpdateUserProfileRequest request) {
		Name name = request.getName() != null ? new Name(request.getName()) : null;
		Surname surname = request.getSurname() != null ? new Surname(request.getSurname()) : null;
		Password password = request.getPassword() != null ? new Password(request.getPassword()) : null;
		Phone phone = request.getPhone() != null ? new Phone(request.getPhone()) : null;
		UpdateUserCommand command = new UpdateUserCommand(
				UserId.fromString(id),
				name,
				surname,
				password,
				phone
		);
		User user = updateUserUseCase.execute(command);
		return Response.ok(UserResponseMapper.fromDomain(user)).build();
	}
	
	/**
	 * Retrieves a user's profile by their unique identifier.
	 * 
	 * @param id the unique identifier of the user to retrieve
	 * @return a response with the user profile
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserProfile(@PathParam("id") String id) {
		GetUserProfileQuery query = new GetUserProfileQuery(UserId.fromString(id));
		User user = getUserProfileUseCase.execute(query);
		return Response.ok(UserResponseMapper.fromDomain(user)).build();
	}

	/**
	 * Deletes an existing user.
	 * 
	 * @param id the unique identifier of the user to delete
	 * @return a response with no content
	 */
	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") String id) {
		DeleteUserCommand command = new DeleteUserCommand(UserId.fromString(id));
		deleteUserUseCase.execute(command);
		return Response.noContent().build();
	}
	
}
