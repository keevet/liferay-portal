package com.liferay.training.blog.statistics.rest.internal.query.jaxrs;

import com.liferay.training.blog.statistics.rest.internal.query.InvalidQueryException;

import java.util.Collections;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + 
        "=(osgi.jaxrs.name=Blog.Statistics.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true"
	},
	service = ExceptionMapper.class
)
@Provider
public class InvalidQueryExceptionMapper
	implements ExceptionMapper<InvalidQueryException> {

	@Override
	public Response toResponse(InvalidQueryException invalidQueryException) {
		return Response.status(
			Response.Status.BAD_REQUEST
		).entity(
			Collections.singletonMap("error", invalidQueryException.getMessage())
		).type(
			MediaType.APPLICATION_JSON
		).build();
	}

}