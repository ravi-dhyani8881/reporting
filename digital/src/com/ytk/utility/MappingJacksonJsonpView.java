package com.ytk.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

public class MappingJacksonJsonpView extends AbstractView
{
	/**
	 * Default content type. Overridable as bean property.
	 */
	public static final String DEFAULT_CONTENT_TYPE = "application/json";
	
	private JsonEncoding encoding = JsonEncoding.UTF8;

	private ObjectMapper objectMapper = new ObjectMapper();

	private boolean prefixJson = false;
	
	private Set<String> renderedAttributes;

	public MappingJacksonJsonpView()
	{
		setContentType(DEFAULT_CONTENT_TYPE);
	}

	protected Object filterModel(Map<String, Object> model)
	{
		Map<String, Object> result = new HashMap<String, Object>(model.size());
		Set<String> renderedAttributes = !CollectionUtils.isEmpty(this.renderedAttributes) ? this.renderedAttributes : model.keySet();
		for (Map.Entry<String, Object> entry : model.entrySet())
		{
			if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey()))
			{
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType(getContentType());
		response.setCharacterEncoding(encoding.getJavaName());
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Object value = filterModel(model);
		JsonGenerator generator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), encoding);
		String callback = request.getParameter("jsoncallback");
		prefixJson = false;
		if (callback!=null)
		{
			prefixJson = true;
		}
		if (prefixJson)
		{
			generator.writeRaw(callback + "(");
		}
		objectMapper.writeValue(generator, value);
		generator.flush();

		if (prefixJson)
		{
			generator.writeRaw(");");
			generator.flush();
		}
	}

	public void setEncoding(JsonEncoding encoding)
	{
		Assert.notNull(encoding, "'encoding' must not be null");
		this.encoding = encoding;
	}

	public void setObjectMapper(ObjectMapper objectMapper)
	{
		Assert.notNull(objectMapper, "'objectMapper' must not be null");
		this.objectMapper = objectMapper;
	}

	public void setPrefixJson(boolean prefixJson)
	{
		this.prefixJson = prefixJson;
	}

	public void setRenderedAttributes(Set<String> renderedAttributes)
	{
		this.renderedAttributes = renderedAttributes;
	}


}
