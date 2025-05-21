import hyRequest from '../..'

const getUserInfoReq = (userInfo: any) => {
  return hyRequest.instance({
    url: '/login/account',
    method: 'POST',
    data: userInfo,
  })
}

const getVerifyCodeReq = (phoneNo: string) => {
  return hyRequest.instance({
    url: 'login/verify-code',
    method: 'POST',
    data: phoneNo,
  })
}

export { getUserInfoReq, getVerifyCodeReq }
