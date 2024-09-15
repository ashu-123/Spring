package contracts.credit

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/credit-scores'
        body(
                "citizenNumber": 1234,
                "requestedDate": anyDate()
        )
        headers {
            contentType applicationJson()
        }
    }

    response {
        status 200
        body(
                "score": "HIGH",
                "uuid": $(consumer("550e8400-e29b-41d4-a716-446655440000"), producer(anyUuid()))
        )
        headers {
            contentType applicationJson()
        }
    }
}