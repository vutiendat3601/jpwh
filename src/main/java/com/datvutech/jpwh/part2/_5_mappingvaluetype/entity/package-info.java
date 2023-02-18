@GenericGenerator(
    name = "id_generator", 
    strategy = "enhanced-sequence", 
    parameters = {
        @Parameter(name = "sequence_name", value = "jpwh_sequence"),
        @Parameter(name = "initial_value", value = "1")
    }
)

package com.datvutech.jpwh.part2._5_mappingvaluetype.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;