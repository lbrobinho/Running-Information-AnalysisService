package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Data
@RequiredArgsConstructor
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalInfo {

    private final long bfr;

    private final long fmi;


}

