from rest_framework import serializers

import re
class SearchRequest(serializers.Serializer):
    """Your data serializer, define your fields here."""
    content = serializers.CharField()
    count = serializers.IntegerField(default=4, initial=4)




class DetailRequest(serializers.Serializer):
    """Your data serializer, define your fields here."""
    caseNumber = serializers.CharField()

    def is_valid(self, *, raise_exception=False):
        if super().is_valid(raise_exception=raise_exception):
            pattern = r'^\d+[가-힣]+\d+$'
            caseNumber = self['caseNumber'].value
            return re.match(pattern,caseNumber) !=None
