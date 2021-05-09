package com.igorkol.everyday_note_backend.authorization.requests

class SignupRequest {

    var username: String? = null

    var email: String? = null
    var role: Set<String>? = null

    var password: String? = null
}
