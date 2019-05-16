package org.oscm.rest.common;

import javax.ws.rs.core.*;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ResponseFactory {

        private Response response;

        public static Response getInstance(ResponseType responseType) {
                return  new Response() {
                        @Override
                        public int getStatus() {
                                return responseType.getStatusCode();
                        }

                        @Override
                        public StatusType getStatusInfo() {
                                return null;
                        }

                        @Override
                        public Object getEntity() {
                                return null;
                        }

                        @Override
                        public <T> T readEntity(Class<T> entityType) {
                                return null;
                        }

                        @Override
                        public <T> T readEntity(GenericType<T> entityType) {
                                return null;
                        }

                        @Override
                        public <T> T readEntity(Class<T> entityType,
                                Annotation[] annotations) {
                                return null;
                        }

                        @Override
                        public <T> T readEntity(GenericType<T> entityType,
                                Annotation[] annotations) {
                                return null;
                        }

                        @Override
                        public boolean hasEntity() {
                                return false;
                        }

                        @Override
                        public boolean bufferEntity() {
                                return false;
                        }

                        @Override
                        public void close() {

                        }

                        @Override
                        public MediaType getMediaType() {
                                return null;
                        }

                        @Override
                        public Locale getLanguage() {
                                return null;
                        }

                        @Override
                        public int getLength() {
                                return 0;
                        }

                        @Override
                        public Set<String> getAllowedMethods() {
                                return null;
                        }

                        @Override
                        public Map<String, NewCookie> getCookies() {
                                return null;
                        }

                        @Override
                        public EntityTag getEntityTag() {
                                return null;
                        }

                        @Override
                        public Date getDate() {
                                return null;
                        }

                        @Override
                        public Date getLastModified() {
                                return null;
                        }

                        @Override
                        public URI getLocation() {
                                return null;
                        }

                        @Override
                        public Set<Link> getLinks() {
                                return null;
                        }

                        @Override
                        public boolean hasLink(String relation) {
                                return false;
                        }

                        @Override
                        public Link getLink(String relation) {
                                return null;
                        }

                        @Override
                        public Link.Builder getLinkBuilder(String relation) {
                                return null;
                        }

                        @Override
                        public MultivaluedMap<String, Object> getMetadata() {
                                return null;
                        }

                        @Override
                        public MultivaluedMap<String, String> getStringHeaders() {
                                return null;
                        }

                        @Override
                        public String getHeaderString(String name) {
                                return null;
                        }
                };
        }
}