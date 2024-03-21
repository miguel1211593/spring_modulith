package org.springframework.samples.Owner;

import org.springframework.samples.Owner.model.Owner;

public interface OwnerInternalAPI {

	Owner findById(Integer id);

}
