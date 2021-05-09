package com.igorkol.everyday_note_backend.authorization.requests

class JwtResponse(
    var accessToken: String,
    var id: Long,
    var username: String,
    var email: String,
    val roles: List<String>
) {
    var tokenType = "Bearer"

}
