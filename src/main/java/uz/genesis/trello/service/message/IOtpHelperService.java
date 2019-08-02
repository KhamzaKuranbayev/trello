package uz.genesis.trello.service.message;

import uz.genesis.trello.domain.auth.User;

public interface IOtpHelperService {
    void send(User user);
    String generateOpt(User user);
}
