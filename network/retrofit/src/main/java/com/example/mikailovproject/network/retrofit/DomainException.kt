package com.example.mikailovproject.network.retrofit

sealed class DomainException(message: String) : RuntimeException(message) {
    class BadRequestException(message: String) : DomainException(message)
    class UnauthorizedException(message: String) : DomainException(message)
    class ForbiddenException(message: String) : DomainException(message)
    class ServerErrorException(message: String) : DomainException(message)
    class NotFoundException(message: String) : DomainException(message)
    class UnknownException(message: String) : DomainException(message)
}