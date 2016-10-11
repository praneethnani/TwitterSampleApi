package twitter.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import twitter.model.ErrorMessage;

@Provider
public class DatabaseExceptionMapper implements ExceptionMapper<RedisException> {

	@Override
	public Response toResponse(RedisException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500,
				"http://localhost:9928/twitter/webapi");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}

}
