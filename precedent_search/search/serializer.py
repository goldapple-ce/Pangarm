from rest_framework import serializers


class SearchRequest(serializers.Serializer):
    """Your data serializer, define your fields here."""
    content = serializers.CharField()
    count = serializers.IntegerField(default=4, initial=4)
