package org.test.task;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepetitiveDomainsCounterTest {

  private final RepetitiveDomainsCounter repetitiveDomainsCounter = new RepetitiveDomainsCounter();

  @Test
  public void theEmailsDomainsShouldBeCount() {
    List<String> emails = List.of(
        "mail1@domain1.com",
        "mail2@domain1.com",
        "mail1@domain2.com",
        "mail2@domain2.com",
        "mail3@domain2.com",
        "mail1@domain3.com",
        "mail2@domain4.com",
        "mail3@domain4.com");

    List<DomainCount> domainsCount = repetitiveDomainsCounter.countRepetitiveDomains(emails);

    assertEquals(2, findDomain(domainsCount, "domain1.com").count());
    assertEquals(3, findDomain(domainsCount, "domain2.com").count());
    assertEquals(1, findDomain(domainsCount, "domain3.com").count());
    assertEquals(2, findDomain(domainsCount, "domain4.com").count());
  }

  @Test
  public void notMoreThenTenDomainsShouldBePresentInTheResult() {
    List<String> emails = List.of(
        "mail@domain1.com",
        "mail@domain2.com",
        "mail@domain3.com",
        "mail@domain4.com",
        "mail@domain5.com",
        "mail@domain6.com",
        "mail@domain7.com",
        "mail@domain8.com",
        "mail@domain9.com",
        "mail@domain10.com",
        "mail@domain11.com",
        "mail@domain12.com");

    List<DomainCount> domainsCount = repetitiveDomainsCounter.countRepetitiveDomains(emails);

    assertEquals(10, domainsCount.size());
  }

  @Test
  public void theRepetitiveDomainsCountShouldBeInOrderFromMaxToMin() {
    List<String> emails = List.of(
        "mail1@domain1.com",
        "mail2@domain1.com",
        "mail1@domain2.com",
        "mail2@domain2.com",
        "mail3@domain2.com",
        "mail1@domain3.com",
        "mail2@domain4.com",
        "mail3@domain4.com",
        "mail3@domain4.com",
        "mail3@domain4.com");

    List<DomainCount> domainsCount = repetitiveDomainsCounter.countRepetitiveDomains(emails);

    Iterator<DomainCount> iterator = domainsCount.iterator();

    assertEquals("domain4.com", iterator.next().domain());
    assertEquals("domain2.com", iterator.next().domain());
    assertEquals("domain1.com", iterator.next().domain());
    assertEquals("domain3.com", iterator.next().domain());
  }

  private DomainCount findDomain(List<DomainCount> domainCounts, String domain) {
    return domainCounts.stream()
        .filter(it -> it.domain().equals(domain))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("The domain is not found. " + domain));
  }
}
