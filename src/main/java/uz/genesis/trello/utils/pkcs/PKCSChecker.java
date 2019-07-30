package uz.genesis.trello.utils.pkcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.genesis.trello.config.security.CustomPermissionEvaluator;
import uz.genesis.trello.repository.auth.UserRepository;
import uz.genesis.trello.utils.UserSession;

@Component
public class PKCSChecker {

    private UserRepository defRepository;
    private UserSession userSession;
    private CustomPermissionEvaluator evaluator;

    @Autowired
    public PKCSChecker(UserRepository defRepository, UserSession userSession, CustomPermissionEvaluator evaluator) {
        this.defRepository = defRepository;
        this.userSession = userSession;
        this.evaluator = evaluator;
    }

    public String generateUniquePublicKey() {
        String defPrivilege = evaluator.hasUserAuth();
        return Crypto.encodeBase(defPrivilege, String.valueOf(userSession.getUser().getOrganizationId()));
    }

    public String decrypt(String encodedText) {
        return Crypto.decodeBase(evaluator.hasUserAuth(), encodedText);
    }
}
