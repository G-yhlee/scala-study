#### JSON 데이터 흐름

```md
Author( name = "yun" bio = "...") => Encoder[Author] => io.circe.Json => \_.spaces2 => {"name": "kate","bio": "..." }
Author( name = "yun" bio = "...") => Decoder[Author] <= io.circe.Json <= io.circe.parser <= {"name": "kate","bio": "..." }
```
