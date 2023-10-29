package org.test.task;

import java.util.*;

/**
 * Things to improve:
 *  - Add validation to count only valid emails
 *  - Split count and represent logic
 *  - Add ability to read emails from the file
 *  - Add ability to process file with emails by batches, in case the file is too big to read and process at once
 */

public class RepetitiveDomainsCounter {

  public List<DomainCount> countRepetitiveDomains(List<String> emails) {
    Map<String, Long> domainsCountMap = new TreeMap<>();

    emails.stream()
        .map(email -> email.substring(email.indexOf("@") + 1))
        .forEach(domain -> {
          domainsCountMap.computeIfAbsent(domain, key -> 0L);
          domainsCountMap.computeIfPresent(domain, (key, value) -> ++value);
        });

    return domainsCountMap.entrySet()
        .stream()
        .limit(10)
        .map(entry -> new DomainCount(entry.getKey(), entry.getValue()))
        .sorted(Comparator.comparing(DomainCount::count, Comparator.reverseOrder()))
        .toList();
  }
}
