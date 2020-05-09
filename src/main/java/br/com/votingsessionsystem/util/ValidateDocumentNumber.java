package br.com.votingsessionsystem.util;

import br.com.votingsessionsystem.enums.DocumentNumberValidator;
import br.com.votingsessionsystem.exception.AssociatedDocumentNumberUnableToVote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ValidateDocumentNumber {

    private static final String URL_API_USERS_INFO = "https://user-info.herokuapp.com/users/";

    public static void checkDocumentNumberIsValid(String documentNumber) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL_API_USERS_INFO +  documentNumber;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        String status = String.valueOf(root.path("status")).replace("\"", "");

        if (DocumentNumberValidator.UNABLE_TO_VOTE.name().equals(status)) {
            throw new AssociatedDocumentNumberUnableToVote("Document Number of Associated unable to vote.");
        }
    }
}
