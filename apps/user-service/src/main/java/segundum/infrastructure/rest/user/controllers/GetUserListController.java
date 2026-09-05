package segundum.infrastructure.rest.user.controllers;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import segundum.application.queries.GetUserListQuery;
import segundum.application.readmodels.user.UserInfoReadModel;
import segundum.application.usecases.GetUserListUseCase;
import segundum.infrastructure.rest.user.api.GetUserListApi;
import segundum.infrastructure.rest.user.responses.UserListResponse;
import segundum.infrastructure.rest.user.responses.UserListResponse.UserResource;

/**
 * Represents the controller that handles the retrieval of all user info.
 */
public class GetUserListController implements GetUserListApi {

	/**
	 * The use case for retrieving all user info.
	 */
	private final GetUserListUseCase getUserListUseCase;

	/**
	 * The URI information injected by the JAX-RS runtime.
	 */
	@Context
	private UriInfo uriInfo;

	/**
	 * Constructs a new GetUserListController with the given use case.
	 *
	 * @param getUserListUseCase the use case for retrieving all user info
	 */
	public GetUserListController(GetUserListUseCase getUserListUseCase) {
		this.getUserListUseCase = getUserListUseCase;
	}

	/**
	 * Retrieves all user info.
	 *
	 * @return a response containing the list of user info
	 */
	@Override
	public Response getUserList() {
		GetUserListQuery query = new GetUserListQuery();
		List<UserInfoReadModel> infos = getUserListUseCase.execute(query);
		List<UserResource> resources = new LinkedList<>();
		infos.forEach(info -> {
			UserResource resource = new UserResource();
			resource.setInfo(info);
			String id = info.getId();
			URI newUrl = uriInfo.getAbsolutePathBuilder().path(id).build();
			resource.setUrl(newUrl.toString());
			resources.add(resource);
		});
		UserListResponse list = new UserListResponse();
		list.setResources(resources);
		return Response.ok(list).build();
	}

}
