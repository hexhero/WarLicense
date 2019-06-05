#!/usr/bin/python python3
import json,encoder,re

keys = [input('IP: '),input('MAC: '),input('Begin_date[yyyy-MM-dd]: '),input('End_date[yyyy-MM-dd]: ')]
print(':'.join(keys))
enc = encoder.encode(':'.join(keys))
with open("cert",'wb') as f:
    f.write(enc)
