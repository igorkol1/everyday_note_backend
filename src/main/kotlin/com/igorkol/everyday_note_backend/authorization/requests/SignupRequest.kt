package com.igorkol.everyday_note_backend.authorization.requests

class SignupRequest(
    var username: String,
    var email: String,
    var role: Set<String>,
    var password: String
) {
}
