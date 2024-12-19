package com.medical.service;

import com.medical.entity.Token;
import com.medical.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    public Optional<Token> getTokenById(Long id) {
        return tokenRepository.findById(id);
    }

    public List<Token> getTokensByCustomerId(Long customerId) {
        return tokenRepository.findByCustomersId(customerId);
    }

    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    public void deleteToken(Long id) {
        tokenRepository.deleteById(id);
    }
}
