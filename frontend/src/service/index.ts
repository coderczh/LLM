// service统一出口
import HYRequest from './request/index.ts'
import { BASE_URL, TIME_OUT } from './config/index.ts'

const hyRequest = new HYRequest({
  baseURL: BASE_URL,
  timeout: TIME_OUT,
  interceptors: {
    requestInterceptor: (config) => {
      config.headers.setContentType('application/json')
      config.headers.setAuthorization('Bearer sk-izxrtjnozdcvzttaqnrxdpphariewkiskaldqpitbcoafucr')
      return config
    },
    /*       requestInterceptorCatch: (err) => {
        console.log('请求失败的拦截')
        return err
      },
      responseInterceptor: (res) => {
        console.log('请求成功的拦截')
        return res
      },
      responseInterceptorCatch: (err) => {
        console.log('响应成功的拦截')
        return err
      } */
  },
})

export default hyRequest
