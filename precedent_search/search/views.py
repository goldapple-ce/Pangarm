from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from .serializer import SearchRequest
from . import service

INPUT_ERROR_RESPONSE = Response(
    {"code": "P003",
     "message": "인풋값에 문제가 생겼습니다. 다시 시도해 주세요."
     },
    status=status.HTTP_400_BAD_REQUEST)


@api_view(['POST'])
def search(request):
    search_request = SearchRequest(data=request.data)
    if not search_request.is_valid():
        return INPUT_ERROR_RESPONSE

    response = service.search(search_request)
    return response
