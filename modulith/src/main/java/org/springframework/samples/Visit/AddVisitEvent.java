package org.springframework.samples.Visit;

import java.util.Set;

public record AddVisitEvent(VisitDTO visitDTO, Integer pet_id) {
}
