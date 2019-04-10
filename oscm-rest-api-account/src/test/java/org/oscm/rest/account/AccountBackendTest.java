/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.AccountService;
import org.oscm.internal.intf.OperatorService;

@ExtendWith(MockitoExtension.class)
public class AccountBackendTest {
  // TODO: These tests should be in fact integration tests

  @Mock private AccountService accountService;
  @Mock private OperatorService operatorService;
  @InjectMocks private AccountBackend backend;

  @BeforeEach
  public void setUp() {}

  @Test
  @Disabled("Not implemented")
  public void shouldPostBillingContact() {}

  @Test
  @Disabled("Not implemented")
  public void shouldGetBillingContactCollection() {}

  @Test
  @Disabled("Not implemented")
  public void shouldGetBillingContact() {}

  @Test
  @Disabled("Not implemented")
  public void shouldPutBillingContact() {}

  @Test
  @Disabled("Not implemented")
  public void shouldDeleteBillingContact() {}

  @Test
  @Disabled("Not implemented")
  public void shouldGetPaymentInfoCollection() {}

  @Test
  @Disabled("Not implemented")
  public void shouldPutPaymentInfo() {}

  @Test
  @Disabled("Not implemented")
  public void shouldGetPaymentInfo() {}

  @Test
  @Disabled("Not implemented")
  public void shouldDeletePaymentInfo() {}

  @Test
  @Disabled("Not implemented")
  public void shouldPostOrganization() {}

  @Test
  @Disabled("Not implemented")
  public void shouldGetOrganization() {}
}
