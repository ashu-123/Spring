import org.springframework.cloud.contract.spec.Contract

Contract.make {
    label("score_of_high")
    input {
        triggeredBy( 'scoreOfHigh()')
    }
    outputMessage {
        sentTo("credit_scores")
        body(
                score: "HIGH",
                uuid: "550e8400-e29b-41d4-a716-446655440000"
        )
        headers {
            header("contentType", applicationJsonUtf8())
        }
    }
}