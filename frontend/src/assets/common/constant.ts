const SUCCESS_CODE = '200'

const verifyPhoneNo = (phoneNo: string) => /^1[3-9]\d{9}$/.test(phoneNo.trim())

const NORMAL_STYLE = {
  'background-color': '',
  'border-color': '',
  color: '#777',
  outline: '',
}
const SELECT_STYLE = {
  'background-color': 'var(--el-button-hover-bg-color)',
  'border-color': 'var(--el-button-hover-border-color)',
  color: 'var(--el-button-hover-text-color)',
  outline: 'none',
}

export { SUCCESS_CODE, NORMAL_STYLE, SELECT_STYLE, verifyPhoneNo }
