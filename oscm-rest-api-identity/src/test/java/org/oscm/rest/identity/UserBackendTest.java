package org.oscm.rest.identity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.IdentityService;

@ExtendWith({MockitoExtension.class})
public class UserBackendTest {
  // TODO: These tests should be in fact integration tests

  @Mock private IdentityService identityService;
  @InjectMocks @Spy private UserBackend userBackend;

  @BeforeEach
  public void setUp() {}

  @Test
  @Disabled("Not implemented")
  public void shouldGetLdapUsers() {}

  @Test
  @Disabled("Not implemented")
  public void shouldPostLdapUsers() {}

  @Test
  @Disabled("Not implemented")
  public void shouldGetUsers() {}

  @Test
  @Disabled("Not implemented")
  public void shouldPostUser() {}

  @Test
  @Disabled("Not implemented")
  public void shouldGetUser() {}

  @Test
  @Disabled("Not implemented")
  public void shouldPutUser() {}

  @Test
  @Disabled("Not implemented")
  public void shouldDeleteUser() {}

  @Test
  @Disabled("Not implemented")
  public void shouldGetRoles() {}

  @Test
  @Disabled("Not implemented")
  public void shouldPutRoles() {}

  @Test
  @Disabled("Not implemented")
  public void shouldPutOnBehalfUser() {}

  @Test
  @Disabled("Not implemented")
  public void shouldDeleteOnBehalfUser() {}
}
