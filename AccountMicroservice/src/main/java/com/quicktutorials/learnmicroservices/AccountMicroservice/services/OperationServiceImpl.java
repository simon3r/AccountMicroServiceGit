package com.quicktutorials.learnmicroservices.AccountMicroservice.services;

import com.quicktutorials.learnmicroservices.AccountMicroservice.entities.Account;
import com.quicktutorials.learnmicroservices.AccountMicroservice.entities.Operation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    @Override
    public List<Operation> getAllOperationPerAccount(String accountId) {
        return null;
    }

    @Override
    public List<Account> getAllAccountsPerUser(String userId) {
        return null;
    }

    @Override
    public Operation saveOperation(Operation operation) {
        return null;
    }
}
