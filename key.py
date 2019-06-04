#!/usr/bin/python python3
import json,encoder,re

key = {
    'start_date':None,
    'valid_days':None,
    'mac':None
}
key['start_date'] = input("输入启用时间yyyy-MM-dd: ")
while re.match(r'\d{4}-\d{2}(-\d{2})?',key['start_date']) is None:
    key['start_date'] = input("时间格式有误,请重新输入yyyy-MM-dd: ")

key['valid_days'] = input("输入使用天数: ")
key['mac'] = input("请输入Mac地址: ")

key_json = json.dumps(key)
enc = encoder.encode(key_json)
with open("cert",'wb') as f:
    f.write(enc)
