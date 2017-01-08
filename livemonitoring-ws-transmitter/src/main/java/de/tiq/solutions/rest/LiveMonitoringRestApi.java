/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tiq.solutions.rest;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.XML;

@Path("/")
public class LiveMonitoringRestApi {

	public LiveMonitoringRestApi() {
	}

	/**
	 * Get the root endpoint Return always 200.
	 *
	 * @return 200 response
	 */
	@GET
	public Response getRoot() {
		return Response.ok().build();
	}

	@GET
	@Path("version")
	public String getVersion() {
		return "was auch immer";
	}

	@GET
	@Path("serverconfig")
	@Produces(MediaType.APPLICATION_JSON)
	public String getServerConfigs(@Context HttpServletRequest req) {
		try {
			java.nio.file.Path path = Paths.get("../config/lm_server_config.xml").toAbsolutePath();
			String string = XML.toJSONObject(new String(Files.readAllBytes(path))).toString();
			return string;
			
//			return new String(Files.readAllBytes(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return " ";
	}

	@GET
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(@Context HttpServletRequest req) {
		req.getSession(true).invalidate();
		return "SUCCESS";
	}

	@GET
	@Path("xml")
	@Produces(MediaType.APPLICATION_XML)
	public RestResponse xml(@Context HttpServletRequest req) {
		RestResponse restResponse = new RestResponse("HAllo");
		return restResponse;
	}
}
