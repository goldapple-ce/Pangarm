from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import exception_handler

NETWORK_ERROR_RESPONSE = Response(
    {"code": "P001",
     "message": "네트워크에 문제가 생겼습니다. 다시 시도해 주세요."
     },
    status=status.HTTP_500_INTERNAL_SERVER_ERROR)

BAD_REQUEST_RESPONSE = Response(
    {"code": "P002",
     "message": "잘못된 요청입니다."
     },
    status=status.HTTP_400_BAD_REQUEST)


def custom_exception_handler(exc, context):
    # Call REST framework's default exception handler first,
    # to get the standard error response.
    response = exception_handler(exc, context)
    if not response is None:
        return BAD_REQUEST_RESPONSE

    return NETWORK_ERROR_RESPONSE
