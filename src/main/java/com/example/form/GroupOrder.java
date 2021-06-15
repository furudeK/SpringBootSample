package com.example.form;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.GroupSequence;

@GroupSequence({VaildGroup1.class,VaildGroup2.class})
public interface GroupOrder {
}
