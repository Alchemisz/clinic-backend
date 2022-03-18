package com.knagmed.clinic.service;

import com.knagmed.clinic.customRequest.VisitRequest;
import com.knagmed.clinic.entity.Visit;

public interface VisitService extends BasicCrudService<Visit, Long> {

    Visit save(VisitRequest visitRequest);

}
