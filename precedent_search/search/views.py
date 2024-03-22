from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from .serializer import SearchRequest, DetailRequest
from . import service

INPUT_ERROR_RESPONSE = Response(
    {"code": "P003",
     "message": "해당 판례는 존재하지 않습니다."
     },
    status=status.HTTP_400_BAD_REQUEST)


@api_view(['POST'])
def search(request):
    search_request = SearchRequest(data=request.data)
    if not search_request.is_valid():
        return INPUT_ERROR_RESPONSE

    response = service.search(search_request)
    return response


@api_view(['GET'])
def findSummaryByCaseNumber(request):
    detail_request = DetailRequest(data=request.query_params)
    if not detail_request.is_valid():
        return INPUT_ERROR_RESPONSE
    response = service.findSummaryByCaseNumber(detail_request['caseNumber'].value)

    return response
