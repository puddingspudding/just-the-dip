
# Bitstamp Trading Bot

## Run

### Build
```
mvn clean package
```
### Properties
| Name | Descritopn | Examples |
|----- | ---------- | ------- | 
| apiKey| Bitsamp api key | 2ad56c2da76ed... |
| apiSecretKey | Bitstamp api secret key | habcdf570da3fe.... |
| totalPct | Row 3 Column 2 | 75 |
| stepsPct | | 5 |
| sellPct | | 5 |
| pair | Currency pair | XLMEUR |
| maxFiat | max amount of fiat money to spent | 500 |

### Command
```
java \
    -jar bot.jar \
    -DapiKey=API_KEY \
    -DapiSecretKey=API_SECRET_KEY \
    -DtotalPct=75 \
    -DstepsPct=5 \
    -DsellPct=5 \
    -Dpair=XLMEUR \
    -DmaxFiat=500
```