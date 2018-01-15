package com.banary.admin.service;

import com.banary.admin.mapper.SystemLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;
}
