interface DialogueInputDto {
  question: string
  model: number
}

interface Record {
  question: string
  answer: string
}

interface UserInfo {
  jobNo: string
  avatar?: string
  nickName?: string
  gender?: string
  password: string
}

interface StyleChange {
  'background-color': string
  'border-color': string
  color: string
  outline: string
}

export { DialogueInputDto, Record, UserInfo, StyleChange }
