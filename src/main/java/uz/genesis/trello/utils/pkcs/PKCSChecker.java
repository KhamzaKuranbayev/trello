package uz.genesis.trello.utils.pkcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.genesis.trello.config.security.CustomPermissionEvaluator;
import uz.genesis.trello.repository.auth.UserRepository;

@Component
public class PKCSChecker {

    private UserRepository defRepository;
    private CustomPermissionEvaluator evaluator;

    @Autowired
    public PKCSChecker(UserRepository repository, CustomPermissionEvaluator evaluator) {
        this.defRepository = repository;
        this.evaluator = evaluator;
    }

    public String generateUniquePublicKey() {
        String defPrivilege = evaluator.hasUserAuth();
        return Crypto.encodeBase(defPrivilege);
    }

    public String decrypt(String text) {
        String key = generateUniquePublicKey();
        return Crypto.decrypt(text, key);
    }
}
