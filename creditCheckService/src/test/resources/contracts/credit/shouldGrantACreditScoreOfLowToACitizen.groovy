package contracts.credit

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/credit-scores'
        body(
                "citizenNumber": 4444,
                "requestedDate": anyDate(),
                "uuid": $(consumer(anyUuid()), producer("550e8400-e29b-41d4-a716-446655440000"))
        )
        headers {
            contentType applicationJson()
        }
    }

    response {
        status 200
        body(
                "score": "LOW",
                "uuid": $(consumer(fromRequest().body('$.uuid')), producer("550e8400-e29b-41d4-a716-446655440000"))
        )
        headers {
            contentType applicationJson()
        }
    }
}